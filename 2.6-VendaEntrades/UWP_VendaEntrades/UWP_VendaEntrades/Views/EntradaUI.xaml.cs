using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using UWP_VendaEntrades.CDs;
using VendaEntradesDM.DB;
using VendaEntradesDM.Models;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Control de usuario está documentada en https://go.microsoft.com/fwlink/?LinkId=234236

namespace UWP_VendaEntrades.Views
{
    public sealed partial class EntradaUI : UserControl
    {
        private int numTotalParcs;
        private DateTime dataSelected;
        private IList<Object> parcsSelected;
        //private List<int> entradesUltimesGenerades;
        private List<Entrada> entradesUltimesGenerades;
        private int idSeguentEntrada = 0;
        private decimal totalEntrades;
        public EntradaUI()
        {
            this.InitializeComponent();
        }

        private void RegenerarEntradesUltimes()
        {
            entradesUltimesGenerades = new List<Entrada>();
            CalcularTotal();
            RefrescarDataGridEntrades();
        }

        private void CalcularTotal()
        {
            totalEntrades = 0;
            foreach(Entrada e in entradesUltimesGenerades)
            {
                totalEntrades += e.Preu;
            }

            tbkTotal.Text = "Total: " + totalEntrades;
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            CarregarParcs();
            CarregarDies();
            CarregarCategories();
            RegenerarEntradesUltimes();
        }

        private void CarregarCategories()
        {
            var llista = Enum.GetValues(typeof(TipusCategoria));
            
            foreach (var tipus in llista)
            {
                cboCategories.Items.Add(tipus.ToString());
            }

            cboCategories.SelectedIndex = 0;

        }

        private void CarregarDies()
        {
            //Fiquem el datapicker que només pugui comprar entrades pel futur.
            dtaEntrada.MinYear = new DateTimeOffset(DateTime.Today);

            int[] dies = { 1, 2, 3};
            cboNumeroDies.ItemsSource = dies;
            cboNumeroDies.SelectedIndex = 0;
        }

        private void CarregarParcs()
        {
            IEnumerable<Parc> llParcs;
            llParcs = ParcDB.GetParcs();

            foreach (Parc p in llParcs)
            {
                ParcUI parc = new ParcUI();
                parc.ElParc = p;
                lsvParcs.Items.Add(parc);
            }
            numTotalParcs = ParcDB.GetNumParcs();
            
        }

        private void btnAfegirEntrada_Click(object sender, RoutedEventArgs e)
        {
            AfegirEntrada();
        }

        private void AfegirEntrada()
        {
            //Previes comprovacions

            if (ComprovarEntrades())
            {
                String categoria = (String)cboCategories.SelectedItem;
                TipusCategoria tipusCat;
                switch (categoria)
                {
                    case "ADULT":
                        tipusCat = TipusCategoria.ADULT;
                        break;
                    case "SENIOR":
                        tipusCat = TipusCategoria.SENIOR;
                        break;
                    default:
                        tipusCat = TipusCategoria.DISCAPACITAT;
                        break;
                }
                int numDies = (int)cboNumeroDies.SelectedItem;
                List<int> codiParcs = new List<int>(numTotalParcs);

                Console.WriteLine("Num parcs:" + numTotalParcs);

                //Afegim els ids dels parcs per buscar el preu.
                for (int i = 0; i < parcsSelected.Count; i++)
                {
                    codiParcs.Add(((ParcUI)parcsSelected[i]).ElParc.Codi);
                }
                codiParcs.Sort();
                //Després afegim els 0 necessaris.
                for (int i = codiParcs.Count-1; i < numTotalParcs; i++)
                {
                    codiParcs.Add(0);
                }

                //Per defecte obté els parcs en ordre de selecció.
                
                if(idSeguentEntrada == 0)
                {
                    idSeguentEntrada = EntradaDB.GetSeguentCodi();
                }
                else
                {
                    idSeguentEntrada++;
                }
                Entrada entrada = new Entrada(idSeguentEntrada,dataSelected, numDies, tipusCat, codiParcs);
                //Guardem les entrades en memoria i si accepta les guardem a la DB.
                entradesUltimesGenerades.Add(entrada);
                
                RefrescarDataGridEntrades();

            }

        }

        private void RefrescarDataGridEntrades()
        {
            dgrEntrades.ItemsSource = null;
            dgrEntrades.ItemsSource = entradesUltimesGenerades;
            dgrEntrades.SelectedIndex = -1;
            CalcularTotal();
        }

        private bool ComprovarEntrades()
        {
            bool totOk = true; 
            dataSelected = dtaEntrada.Date.Date;
            parcsSelected = lsvParcs.SelectedItems;
            if (parcsSelected.Count == 0)
            {
                tbkErrors.Text = "S'ha de seleccionar algún parc per poder afegir l'entrada!";
                totOk = false;
            }
            if (cboNumeroDies.SelectedIndex == -1)
            {
                tbkErrors.Text = "S'ha de els dies!";
                totOk = false;
            }
            if (cboCategories.SelectedIndex == -1)
            {
                tbkErrors.Text = "S'ha de seleccionar una categoria!";
                totOk = false;
            }

            if (totOk)
            {
                tbkErrors.Text = "";
            }

            return totOk;

        }

        private void btnComprar_Click(object sender, RoutedEventArgs e)
        {
            //Comprem totes les entrades que hi hagi a la llista
            // Es a dir les inserim a la BD.
            foreach(Entrada entrada in entradesUltimesGenerades)
            {
                EntradaDB.InsertEntrada(entrada);
                foreach(int codi in entrada.Parcs)
                {
                    if (codi != 0)
                    {
                        EntradaParcDB.InsertEntradaParc(entrada.Id, codi);
                    }
                    
                }
            }


            //Un cop finalitzat vol dir que hem comprat totes les entrades.
            //Ho enunciem al venedor.
            if (entradesUltimesGenerades.Count > 0)
            {
                EntradesCompradesDialog();
                RegenerarEntradesUltimes();
            }

        }

        private async void EntradesCompradesDialog()
        {

            EntradesCompradesCD compraEntradesDialog = new EntradesCompradesCD(entradesUltimesGenerades);
            compraEntradesDialog.FullSizeDesired = true;
            await compraEntradesDialog.ShowAsync();
            /*
            ContentDialog CompraEntradesDialog = new ContentDialog()
            {
                Title = "Entrades",
                Content = "Entrades comprades!",
                CloseButtonText = "Ok"
            };

            await CompraEntradesDialog.ShowAsync();
            */
        }

        private void btnEliminarEntrada_Click(object sender, RoutedEventArgs e)
        {
            if (dgrEntrades.SelectedIndex != -1)
            {
                Entrada entr = (Entrada)dgrEntrades.SelectedItem;
                entradesUltimesGenerades.Remove(entr);
                RefrescarDataGridEntrades();
            }
        }
    }
}

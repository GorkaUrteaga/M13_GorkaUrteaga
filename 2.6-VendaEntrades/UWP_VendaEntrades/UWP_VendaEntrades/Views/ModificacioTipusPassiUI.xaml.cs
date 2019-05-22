using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using VendaEntradesDM.DB;
using VendaEntradesDM.Models;
using Windows.Foundation;
using Windows.Foundation.Collections;
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
    public sealed partial class ModificacioTipusPassiUI : UserControl
    {
        private IEnumerable<TipusPassi> llTipusPassi;
        private int idTipusPassiSeleccionat = 0;
        private ObservableCollection<TipusPassiAtraccio> llTipusPassiAtraccio = new ObservableCollection<TipusPassiAtraccio>();
        private int posicioPassiSeleccionat = 0;

        public ModificacioTipusPassiUI()
        {
            this.InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            CarregarTipusAcces();
            CarregarTipusPassis();
            if(llTipusPassi.Count() == 0)
            {
                NouTipusPassi();
            }
        }

        private void CarregarTipusPassis()
        {
            llTipusPassi = TipusPassiDB.GetTipusPassis();
            lsvTipusPassi.Items.Clear();
            foreach (TipusPassi tp in llTipusPassi)
            {
                lsvTipusPassi.Items.Add(tp.ToString());
            }
            posicioPassiSeleccionat = (lsvTipusPassi.Items.Count() - 1);
            lsvTipusPassi.SelectedIndex = posicioPassiSeleccionat;
            CarregarDadesTipusPassi();
        }

        private void CarregarTipusAcces()
        {
            IEnumerable<TipusAcces> llTipusAcces;
            llTipusAcces = TipusAccesDB.GetTipusAcces();
            foreach(TipusAcces ta in llTipusAcces)
            {
                cboTipusAcces.Items.Add(ta.ToString());
            }
        }

        private void lsvTipusPassi_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            txbNomTipusPassi.Text = "";
            txbPreuPerDia.Text = "";
            posicioPassiSeleccionat = lsvTipusPassi.SelectedIndex;
            if (posicioPassiSeleccionat != -1)
            {
                CarregarDadesTipusPassi();
            }
            llTipusPassiAtraccio = TipusPassiAtraccioDB.GetTipusPassiAtraccio(idTipusPassiSeleccionat);
            CarregarParcsAmbTipusAcces();
            
        }

        private void CarregarDadesTipusPassi()
        {
            if (posicioPassiSeleccionat != -1)
            {
                //int posTipusPassi = lsvTipusPassi.SelectedIndex;
                TipusPassi tp = llTipusPassi.ElementAt<TipusPassi>(posicioPassiSeleccionat);

                idTipusPassiSeleccionat = tp.Id;

                tbkModificacio.Text = "EDICIO";
                txbNomTipusPassi.Text = tp.Nom;
                txbPreuPerDia.Text = tp.PreuPerDia.ToString();
                CarregarParcsAmbTipusAcces();
            }

        }

        private void CarregarParcsAmbTipusAcces()
        {
            
            lsvAtraccions.Items.Clear();

            foreach (TipusPassiAtraccio tp in llTipusPassiAtraccio)
            {
                TipusPassiAtraccioUI tpa = new TipusPassiAtraccioUI();
                tpa.ElTipusPassiAtraccio = tp;
                lsvAtraccions.Items.Add(tpa);
            }
        }

        private void txbPreuPerDia_TextChanged(object sender, TextChangedEventArgs e)
        {
            decimal preu;
            
            if(!Decimal.TryParse(txbPreuPerDia.Text, out preu))
            {
                txbPreuPerDia.Text = "";
            }
        }

        private void btnEsborrarTipusPassi_Click(object sender, RoutedEventArgs e)
        {
            EsborrarTipusPassiAsync();
            
        }

        private async System.Threading.Tasks.Task EsborrarTipusPassiAsync()
        {
            if (posicioPassiSeleccionat > 0)
            {
                ContentDialog eliminarTipusPassiCD = new ContentDialog()
                {
                    Title = "ALERTA!",
                    Content = "Estas segur que vols eliminar el Tipus de Passi.",
                    PrimaryButtonText = "Acceptar",
                    SecondaryButtonText = "Cancelar"
                };

                ContentDialogResult res = await eliminarTipusPassiCD.ShowAsync();

                if(res == ContentDialogResult.Primary){
                    EsborrarTipusPassi();
                }
            }
        }

        private void EsborrarTipusPassi()
        {
            TipusPassiDB.DeleteTipusPassi(idTipusPassiSeleccionat);
            CarregarTipusPassis();
        }

        private void btnNouTipusPassi_Click(object sender, RoutedEventArgs e)
        {
            NouTipusPassi();
        }

        private void NouTipusPassi()
        {
            tbkModificacio.Text = "CREACIO";
            llTipusPassiAtraccio.Clear();
            idTipusPassiSeleccionat = TipusPassiDB.GetSeguentId();
            txbNomTipusPassi.Text = "";
            txbPreuPerDia.Text = "";

            //Carreguem les atraccions en memoria
            foreach (Atraccio a in AtraccioDB.GetAtraccions())
            {
                TipusPassiAtraccio tpa = new TipusPassiAtraccio(idTipusPassiSeleccionat, a.Codi, "UN_SOL_US", a.Nom, a.UrlFoto);
                llTipusPassiAtraccio.Add(tpa);
            }
            
            CarregarParcsAmbTipusAcces();
        }

        private void btnGuardarTipusPasi_Click(object sender, RoutedEventArgs e)
        {
            if (!ComprovacionsTipusPassi()) return;
            TipusPassi tp = new TipusPassi(idTipusPassiSeleccionat, txbNomTipusPassi.Text, Decimal.Parse(txbPreuPerDia.Text));
            if (TipusPassiDB.ExisteixTipusPassi(idTipusPassiSeleccionat))
            {
                
                TipusPassiDB.UpdateTipusPassi(tp);
                foreach(TipusPassiAtraccio tpa in llTipusPassiAtraccio)
                {
                    TipusAcces ta = TipusAccesDB.GetUnTipusAccesPerNom(tpa.TipusAcces);
                    TipusPassiAtraccioDB.UpdateTipusPassiAtraccio(tpa, ta.Id);
                }
                
            }
            else
            {
                //Fem insert al tipus de passi, a la taula de relacions i a la taula per ficar els comptadors a 0.
                //TipusPassiDB.I
                TipusPassiDB.InsertTipusPassi(tp);
                foreach(TipusPassiAtraccio tpa in llTipusPassiAtraccio)
                {
                    TipusAcces ta = TipusAccesDB.GetUnTipusAccesPerNom(tpa.TipusAcces);
                    TipusPassiAtraccioDB.InsertTipusPassiAtraccio(tpa, ta.Id);
                }
                
            }
            llTipusPassiAtraccio = TipusPassiAtraccioDB.GetTipusPassiAtraccio(idTipusPassiSeleccionat);
            CarregarTipusPassis();
            CarregarParcsAmbTipusAcces();
            CarregarDadesTipusPassi();
        }

        private bool ComprovacionsTipusPassi()
        {
            bool totOk = true;
            if (txbNomTipusPassi.Text.Trim().Equals(""))
            {
                tbkErrors.Text = "S'ha de introduir un nom correcte al tipus de passi.";
                totOk = false;
            }
            if (txbPreuPerDia.Text.Equals(""))
            {
                tbkErrors.Text = "S'ha de introduir un preu correcte al tipus de passi.";
                totOk = false;
            }

            return totOk;
        }

        private void btnAplicarTipusAccesAtotes_Click(object sender, RoutedEventArgs e)
        {
            if(cboTipusAcces.SelectedIndex != -1)
            {
                foreach (TipusPassiAtraccio tpa in llTipusPassiAtraccio)
                {
                    tpa.TipusAcces = (String)cboTipusAcces.SelectedItem;
                }
                CarregarParcsAmbTipusAcces();
            }
        }

        private void btnAplicarTipusAcces_Click(object sender, RoutedEventArgs e)
        {
            if(lsvAtraccions.SelectedIndex != -1 && cboTipusAcces.SelectedIndex != -1)
            {
                llTipusPassiAtraccio[lsvAtraccions.SelectedIndex].TipusAcces = (String)cboTipusAcces.SelectedItem;
                CarregarParcsAmbTipusAcces();
            }
        }
    }
}

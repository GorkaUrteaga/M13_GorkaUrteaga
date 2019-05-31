using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using UWP_VendaEntrades.CDs;
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
    public sealed partial class PassiUI : UserControl
    {
        private ObservableCollection<Client> clientsPassis;
        private int idSeguentClient = 0;
        private Client clientSeleccionat = null;
        private int idSeguentPassi = 0;
        private int idTipusPassiSeleccionat = -1;
        private IEnumerable<TipusPassi> llTipusPassi;
        private IEnumerable<TipusPassiAtraccio> llTipusPassiAtraccio;

        public PassiUI()
        {
            this.InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            CarregarTipusPassis();
            RegenerarClients();
            dtaPassi.MinYear = new DateTimeOffset(DateTime.Today);
        }

        private void RegenerarClients()
        {
            clientsPassis = new ObservableCollection<Client>();
            RefrescarDataGridClients();
        }

        private void CarregarTipusPassis()
        {
            llTipusPassi = TipusPassiDB.GetTipusPassis();

            foreach (TipusPassi tp in llTipusPassi)
            {
                cboTipusPassi.Items.Add(tp.ToString());
            }

        }

        private void btnAfegirClient_Click(object sender, RoutedEventArgs e)
        {
            //Obrim un quadre de dialeg on l'usuari introduira les dades.
            AfegirClientAsync();
        }

        private async System.Threading.Tasks.Task AfegirClientAsync()
        {
            AfegirClientCD cd = new AfegirClientCD();
            ContentDialogResult res = await cd.ShowAsync();

            //L'afegim a la llista de clients actuals
            if(res == ContentDialogResult.Primary)
            {
                //Obtenim la id del client.
                if (idSeguentClient == 0)
                {
                    idSeguentClient = ClientDB.GetSeguentCodi();
                }
                else 
                {
                    idSeguentClient++;   
                }
                //Intentem inserir el client sempre comprovant que no existeixi ja.
                if (!clientsPassis.Contains(cd.clientInserit))
                {
                    if(cd.clientInserit.Id == 0)
                    {
                        cd.clientInserit.Id = idSeguentClient;
                    }
                    else
                    {
                        idSeguentClient--;
                    }
                    clientsPassis.Add(cd.clientInserit);
                    RefrescarDataGridClients();
                }
                else
                {
                    ClientJaExistentDialog();
                    idSeguentClient--;
                }
            }
        }

        private void RefrescarDataGridClients()
        {
            dgrClients.ItemsSource = null;
            dgrClients.ItemsSource = clientsPassis;
            dgrClients.SelectedIndex = clientsPassis.Count - 1;
            clientSeleccionat = (Client)dgrClients.SelectedItem;
        }

        private async void ClientJaExistentDialog()
        {
            ContentDialog ClientExistentDialog = new ContentDialog()
            {
                Title = "ERROR!",
                Content = "El client que intenta inserir ja existeix",
                CloseButtonText = "Ok"
            };

            await ClientExistentDialog.ShowAsync();
        }

        private void btnEliminarClient_Click(object sender, RoutedEventArgs e)
        {
            if(dgrClients.SelectedIndex != -1)
            {
                clientsPassis.Remove((Client)dgrClients.SelectedItem);
                RefrescarDataGridClients();
            }
        }

        private void cboTipusPassi_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //Escull un tipus i mostrem les atraccions i el seu tipus d'access.
            idTipusPassiSeleccionat = cboTipusPassi.SelectedIndex+1;

            llTipusPassiAtraccio = TipusPassiAtraccioDB.GetTipusPassiAtraccio(idTipusPassiSeleccionat);
            lsvAtraccions.Items.Clear();
            foreach (TipusPassiAtraccio tp in llTipusPassiAtraccio)
            {
                TipusPassiAtraccioUI tpa = new TipusPassiAtraccioUI();
                tpa.ElTipusPassiAtraccio = tp;
                lsvAtraccions.Items.Add(tpa);
            }

        }

        private void btnAfegirPassi_Click(object sender, RoutedEventArgs e)
        {
            if (ComprovacionsPassi())
            {
                if (idSeguentPassi == 0)
                {
                    idSeguentPassi = PassiDB.GetSeguentCodi();
                }
                else
                {
                    idSeguentPassi++;
                }
                TipusPassi tp = llTipusPassi.ElementAt(idTipusPassiSeleccionat-1);
                Passi p = new Passi(idSeguentPassi, dtaPassi.Date.Date, tp.Id,tp.Nom,tp.PreuPerDia);
                clientSeleccionat.AddPassi(p);

                RefrescarDataGridPassisClient();

            }
        }

        private void RefrescarDataGridPassisClient()
        {
            dgrPassis.ItemsSource = null;
            if (clientSeleccionat != null)
            {
                decimal total = 0;
                dgrPassis.ItemsSource = clientSeleccionat.GetPassis();
                IEnumerable<Passi> passis = clientSeleccionat.GetPassis();
                foreach(Passi p in passis)
                {
                    total += p.Preu;
                }
                tbkTotal.Text = "Total: " + total;
            }
        }

        private bool ComprovacionsPassi()
        {
            bool totOk = true;

            if(idTipusPassiSeleccionat < 0)
            {
                tbkErrors.Text = "S'ha de seleccionar el tipus de passi.";
                totOk = false;
            }
            if(clientSeleccionat == null)
            {
                tbkErrors.Text = "S'ha de seleccionar el client al que li assignarem aquest passi.";
                totOk = false;
            }

            if (totOk) tbkErrors.Text = "";

            return totOk;
        }

        private void dgrPassis_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if(dgrClients.SelectedIndex != -1)
            {
                clientSeleccionat = (Client)dgrClients.SelectedItem;
            }
        }

        private void btnEliminarPassi_Click(object sender, RoutedEventArgs e)
        {
            if(clientSeleccionat != null && dgrPassis.SelectedIndex != -1)
            {
                Passi p = (Passi)dgrPassis.SelectedItem;
                clientSeleccionat.RemovePassi(p);
            }
        }

        private void btnComprarPassis_Click(object sender, RoutedEventArgs e)
        {
            foreach (Client cli in clientsPassis)
            {
                if (!ClientDB.ExisteixClient(cli.Id))
                {
                    ClientDB.InsertClient(cli);
                }

                IEnumerable<TipusPassiAtraccio> tipusPassiAtraccio;

                foreach (Passi p in cli.GetPassis())
                {
                    //A part també hem d'inserir a la taula de Info_Utilitzacio
                    PassiDB.InsertPassi(cli.Id, p);
                    //TipusPassi tp = llTipusPassi.ElementAt(cboTipusPassi.SelectedIndex);
                    tipusPassiAtraccio = TipusPassiAtraccioDB.GetTipusPassiAtraccio(p.IdTipusPassi);
                    foreach (TipusPassiAtraccio tpa in tipusPassiAtraccio)
                    {
                        TipusAcces ta = TipusAccesDB.GetUnTipusAccesPerNom(tpa.TipusAcces);
                        InfoUtilitzacio iu = new InfoUtilitzacio(p.Id, tpa.IdAtraccio, 0,ta.Id);
                        InfoUtilitzacioDB.InsertInfoUtilitzacio(iu);
                    }
                    
                }
            }

            
            RegenerarClients();
            RefrescarDataGridPassisClient();
        }

        private void dgrClients_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            clientSeleccionat = (Client)dgrClients.SelectedItem;
            RefrescarDataGridPassisClient();
        }
    }
}

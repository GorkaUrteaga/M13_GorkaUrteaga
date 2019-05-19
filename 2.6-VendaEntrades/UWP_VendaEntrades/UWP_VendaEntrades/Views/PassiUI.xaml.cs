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

        public PassiUI()
        {
            this.InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            CarregarTipusPassis();
            RegenerarClients();
        }

        private void RegenerarClients()
        {
            clientsPassis = new ObservableCollection<Client>();
        }

        private void CarregarTipusPassis()
        {
            
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
                    cd.clientInserit.Id = idSeguentClient;
                    clientsPassis.Add(cd.clientInserit);
                }
                else
                {
                    ClientJaExistentDialog();
                    idSeguentClient--;
                }
            }
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
    }
}

using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
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

// La plantilla de elemento del cuadro de diálogo de contenido está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace UWP_VendaEntrades.CDs
{
    public sealed partial class AfegirClientCD : ContentDialog
    {
        public Client clientInserit;
        private static TextInfo ti = new CultureInfo("es-ES", false).TextInfo;
        public AfegirClientCD()
        {
            this.InitializeComponent();
        }

        private void ContentDialog_PrimaryButtonClick(ContentDialog sender, ContentDialogButtonClickEventArgs args)
        {
            //Fem previes comprovacions.
            if (ComprovacionsClient())
            {
                //Si el tenim a la BD el carreguem ja i no cal crear-lo de nou.
                clientInserit = ClientDB.GetClient(ti.ToTitleCase(tbxNIF.Text));
                
                if (clientInserit == null)
                {
                    clientInserit = new Client(0, tbxNIF.Text, tbxNom.Text, tbxCognom1.Text, tbxCognom2.Text, null);
                }
            }
            else
            {
                args.Cancel = true;
            }
        }

        private bool ComprovacionsClient()
        {
            bool totOk = true;

            if (tbxNIF.Text.Trim().Length != 9)
            {
                IndicarError(tbxNIF);
                totOk = false;
            }
            if (tbxNom.Text.Trim().Length < 2)
            {
                IndicarError(tbxNom);
                totOk = false;
            }
            if(tbxCognom1.Text.Trim().Length < 2)
            {
                IndicarError(tbxCognom1);
                totOk = false;
            }

            return totOk;
        }

        private void IndicarError(TextBox tbx)
        {
            tbx.Background = new SolidColorBrush(Colors.Tomato);
        }

        private void ContentDialog_SecondaryButtonClick(ContentDialog sender, ContentDialogButtonClickEventArgs args)
        {
            
        }
    }
}

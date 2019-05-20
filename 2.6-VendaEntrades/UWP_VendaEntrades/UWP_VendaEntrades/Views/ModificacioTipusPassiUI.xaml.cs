using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using VendaEntradesDM.DB;
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
        public ModificacioTipusPassiUI()
        {
            this.InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            CarregarTipusAcces();
        }

        private void CarregarTipusAcces()
        {
            cboTipusAcces.ItemsSource = TipusAccesDB.GetTipusAcces(); 
        }
    }
}

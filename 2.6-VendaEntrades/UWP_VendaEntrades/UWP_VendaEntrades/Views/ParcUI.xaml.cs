using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using VendaEntradesDM.Models;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Media.Imaging;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Control de usuario está documentada en https://go.microsoft.com/fwlink/?LinkId=234236

namespace UWP_VendaEntrades.Views
{
    public sealed partial class ParcUI : UserControl
    {


        public Parc ElParc
        {
            get { return (Parc)GetValue(ElParcProperty); }
            set { SetValue(ElParcProperty, value); }
        }

        // Using a DependencyProperty as the backing store for ElParc.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty ElParcProperty =
            DependencyProperty.Register("ElParc", typeof(Parc), typeof(ParcUI), new PropertyMetadata(null, ParcChangedCallbackStatic));

        private static void ParcChangedCallbackStatic(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            ParcUI parc = (ParcUI)d;
            parc.ParcChangedCallback();
        }

        private void ParcChangedCallback()
        {
            tbkNomParc.Text = ElParc.Nom;
            imgParc.Source = new BitmapImage(new Uri(ElParc.UrlFoto));
        }

        public ParcUI()
        {
            this.InitializeComponent();
        }
    }
}

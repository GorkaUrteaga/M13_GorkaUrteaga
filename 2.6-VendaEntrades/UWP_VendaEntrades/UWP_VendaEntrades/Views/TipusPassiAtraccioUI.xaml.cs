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

    public sealed partial class TipusPassiAtraccioUI : UserControl
    {
        public TipusPassiAtraccio ElTipusPassiAtraccio
        {
            get { return (TipusPassiAtraccio)GetValue(ElTipusPassiAtraccioProperty); }
            set { SetValue(ElTipusPassiAtraccioProperty, value); }
        }

        // Using a DependencyProperty as the backing store for ElTipusPassiAtraccio.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty ElTipusPassiAtraccioProperty =
            DependencyProperty.Register("ElTipusPassiAtraccio", typeof(TipusPassiAtraccio), typeof(TipusPassiAtraccioUI), new PropertyMetadata(null, TipusPassiAtraccioChangedCallbackStatic));

        private static void TipusPassiAtraccioChangedCallbackStatic(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            TipusPassiAtraccioUI tpa = (TipusPassiAtraccioUI)d;
            tpa.TipusPassiAtraccioChangedCallback();
        }

        private void TipusPassiAtraccioChangedCallback()
        {
            if(ElTipusPassiAtraccio != null)
            {
                tbkNomAtraccio.Text = ElTipusPassiAtraccio.NomAtraccio;
                tbkTipusAcces.Text = ElTipusPassiAtraccio.TipusAcces;
                imgAtraccio.Source = new BitmapImage(new Uri(ElTipusPassiAtraccio.UrlFotoAtraccio));
            }
        }

        public TipusPassiAtraccioUI()
        {
            this.InitializeComponent();
        }
    }
}

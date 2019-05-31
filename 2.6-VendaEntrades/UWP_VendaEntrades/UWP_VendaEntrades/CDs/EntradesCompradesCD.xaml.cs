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
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento del cuadro de diálogo de contenido está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace UWP_VendaEntrades.CDs
{
    public sealed partial class EntradesCompradesCD : ContentDialog
    {
        public EntradesCompradesCD(List<Entrada> entrades)
        {
            this.InitializeComponent();
            Entrada primera = entrades.First<Entrada>();
            Entrada ultima = entrades.Last<Entrada>();
            String idPrimera = "&IdEntradaInicial=" + primera.Id;
            String idFinal = "&IdEntradaFinal=" + ultima.Id;

            Uri uri = new Uri("http://92.222.27.83:8080/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2Fm2-gurteaga&reportUnit=%2Fm2-gurteaga%2FEntrades&standAlone=true&j_username=m2-gurteaga&j_password=47129014J"+idPrimera+idFinal);
            //Uri del report
            wbvReportEntrades.Source = uri;
            
        }

        private void ContentDialog_PrimaryButtonClick(ContentDialog sender, ContentDialogButtonClickEventArgs args)
        {
        }

        private void ContentDialog_SecondaryButtonClick(ContentDialog sender, ContentDialogButtonClickEventArgs args)
        {
        }
    }
}

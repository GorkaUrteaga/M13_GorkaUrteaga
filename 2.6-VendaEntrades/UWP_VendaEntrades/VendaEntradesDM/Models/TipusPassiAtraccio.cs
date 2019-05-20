using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class TipusPassiAtraccio
    {
        private int idTipusPassi;
        private int idAtraccio;
        private String tipusAcces;
        private String nomAtraccio;
        private String urlFotoAtraccio;

        public TipusPassiAtraccio(int idTipusPassi, int idAtraccio, string tipusAcces, string nomAtraccio, string urlFotoAtraccio)
        {
            IdTipusPassi = idTipusPassi;
            IdAtraccio = idAtraccio;
            TipusAcces = tipusAcces;
            NomAtraccio = nomAtraccio;
            UrlFotoAtraccio = urlFotoAtraccio;
        }

        public int IdTipusPassi { get => idTipusPassi; set => idTipusPassi = value; }
        public int IdAtraccio { get => idAtraccio; set => idAtraccio = value; }
        public string TipusAcces { get => tipusAcces; set => tipusAcces = value; }
        public string NomAtraccio { get => nomAtraccio; set => nomAtraccio = value; }
        public string UrlFotoAtraccio { get => urlFotoAtraccio; set => urlFotoAtraccio = value; }
    }
}

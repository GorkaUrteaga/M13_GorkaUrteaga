using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class InfoUtilitzacio
    {
        private int idPassi;
        private int idAtraccio;
        private int numUsos;
        private int idTipusAcces;

        public InfoUtilitzacio(int idPassi, int idAtraccio, int numUsos, int tipus_acces)
        {
            IdPassi = idPassi;
            IdAtraccio = idAtraccio;
            NumUsos = numUsos;
            IdTipusAcces = tipus_acces;
        }

        public int IdPassi { get => idPassi; set => idPassi = value; }
        public int IdAtraccio { get => idAtraccio; set => idAtraccio = value; }
        public int NumUsos { get => numUsos; set => numUsos = value; }
        public int IdTipusAcces { get => idTipusAcces; set => idTipusAcces = value; }
    }
}

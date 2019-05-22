using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class Atraccio
    {
        private int codi;
        private string nom;
        private string urlFoto;

        public Atraccio(int codi, string nom, string urlFoto)
        {
            Codi = codi;
            Nom = nom;
            UrlFoto = urlFoto;
        }

        public int Codi { get => codi; set => codi = value; }
        public string Nom { get => nom; set => nom = value; }
        public string UrlFoto { get => urlFoto; set => urlFoto = value; }
    }
}

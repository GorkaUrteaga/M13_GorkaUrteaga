using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class Parc
    {
        private int codi;
        private String nom;
        private String urlFoto;

        public Parc(int codi, string nom, string urlFoto)
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

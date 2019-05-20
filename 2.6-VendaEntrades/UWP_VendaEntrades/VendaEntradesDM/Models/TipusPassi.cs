using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class TipusPassi
    {
        private int id;
        private String nom;
        private decimal preuPerDia;

        public TipusPassi(int id, string nom, decimal preuPerDia)
        {
            Id = id;
            Nom = nom;
            PreuPerDia = preuPerDia;
        }

        public int Id { get => id; set => id = value; }
        public string Nom { get => nom; set => nom = value; }
        public decimal PreuPerDia { get => preuPerDia; set => preuPerDia = value; }

        public string ToString()
        {
            return nom + " (" + preuPerDia + "€)";
        }

    }
}

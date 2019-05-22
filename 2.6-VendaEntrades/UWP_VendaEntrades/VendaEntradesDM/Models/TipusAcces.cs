using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class TipusAcces
    {
        private int id;
        private string tipus;

        public TipusAcces(int id, string tipus)
        {
            Id = id;
            Tipus = tipus;
        }

        public int Id { get => id; set => id = value; }
        public string Tipus { get => tipus; set => tipus = value; }

        public string ToString()
        {
            return Tipus;
        }

    }
}

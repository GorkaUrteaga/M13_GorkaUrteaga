using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class Passi
    {
        private int id;
        private DateTime data;
        private int idTipusPassi;
        private String tipusPassi;
        private decimal preu;

        public Passi(int id, DateTime data, int idTipusPassi, string tipusPassi, decimal preu)
        {
            Id = id;
            Data = data;
            IdTipusPassi = idTipusPassi;
            TipusPassi = tipusPassi;
            Preu = preu;
        }

        public int Id { get => id; set => id = value; }
        public DateTime Data { get => data; set => data = value; }
        public int IdTipusPassi { get => idTipusPassi; set => idTipusPassi = value; }
        public string TipusPassi { get => tipusPassi; set => tipusPassi = value; }
        public decimal Preu { get => preu; set => preu = value; }

        public override bool Equals(object obj)
        {
            var passi = obj as Passi;
            return passi != null &&
                   id == passi.id;
        }

        public override int GetHashCode()
        {
            return 1877310944 + id.GetHashCode();
        }
    }
}

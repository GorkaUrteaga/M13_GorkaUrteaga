using System;
using System.Collections.Generic;
using System.Text;
using VendaEntradesDM.DB;

namespace VendaEntradesDM.Models
{
    public enum TipusCategoria
    {
        ADULT,
        SENIOR,
        DISCAPACITAT
    }

    public class Entrada
    {
        private int id;
        private DateTime data;
        private int diesValidesa;
        private decimal preu;
        private TipusCategoria categoria;
        private List<int> parcs;

        public Entrada(int id, DateTime data, int diesValidesa, TipusCategoria categoria, List<int> parcs)
        {
            Id = id;
            Data = data;
            DiesValidesa = diesValidesa;
            Categoria = categoria;
            Parcs = parcs;
            ObtenirPreu();
        }

        private void ObtenirPreu()
        {
            Preu = PreusDB.GetPreu(parcs,categoria, diesValidesa);
        }

        public override bool Equals(object obj)
        {
            var entrada = obj as Entrada;
            return entrada != null &&
                   id == entrada.id;
        }

        public override int GetHashCode()
        {
            return 1877310944 + id.GetHashCode();
        }

        public int Id { get => id; set => id = value; }
        public DateTime Data { get => data; set => data = value; }
        public int DiesValidesa { get => diesValidesa; set => diesValidesa = value; }
        public decimal Preu { get => preu; set => preu = value; }
        public TipusCategoria Categoria { get => categoria; set => categoria = value; }
        public List<int> Parcs { get => parcs; set => parcs = value; }

        public String GetNomParcs
        {
            get
            {
                String nomParcsEntrada = "";
                foreach (int codi in parcs)
                {
                    if(codi != 0)
                    {
                        nomParcsEntrada += ParcDB.GetNomParc(codi) + "/";
                    }
                    
                }

                nomParcsEntrada = nomParcsEntrada.Substring(0, nomParcsEntrada.Length - 1);
                return nomParcsEntrada;
            }

        }



    }
}

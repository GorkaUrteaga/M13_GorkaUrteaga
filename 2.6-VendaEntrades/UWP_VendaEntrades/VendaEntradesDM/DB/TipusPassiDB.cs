using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class TipusPassiDB
    {

        public static ObservableCollection<TipusPassi> GetTipusPassis()
        {
            ObservableCollection<TipusPassi> llista = new ObservableCollection<TipusPassi>();

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        consulta.CommandText = "select * from Tipus_Passi_Express";

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int codi = reader.GetInt32(reader.GetOrdinal("ID"));
                            string nom = reader.GetString(reader.GetOrdinal("NOM"));
                            decimal preu = reader.GetDecimal(reader.GetOrdinal("PREU_PER_DIA"));

                            TipusPassi tp = new TipusPassi(codi, nom, preu);

                            llista.Add(tp);
                        }
                    }
                }
            }
            return llista;
        }

    }
}

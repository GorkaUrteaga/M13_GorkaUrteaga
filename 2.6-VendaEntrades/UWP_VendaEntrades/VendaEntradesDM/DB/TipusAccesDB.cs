using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class TipusAccesDB
    {
        public static ObservableCollection<TipusAcces> GetTipusAcces()
        {
            ObservableCollection<TipusAcces> llista = new ObservableCollection<TipusAcces>();

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        consulta.CommandText = "select * from tipus_acces";

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int codi = reader.GetInt32(reader.GetOrdinal("ID"));
                            string tipus = reader.GetString(reader.GetOrdinal("TIPUS"));
                            

                            TipusAcces ta = new TipusAcces(codi, tipus);

                            llista.Add(ta);
                        }
                    }
                }
            }
            return llista;
        }

        public static TipusAcces GetUnTipusAccesPerNom(string tipusAcces)
        {
            TipusAcces ta = null;

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        DBUtils.CrearParametre("TipusParam", tipusAcces, consulta);
                        consulta.CommandText = "select * from tipus_acces where tipus = @TipusParam";

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int codi = reader.GetInt32(reader.GetOrdinal("ID"));
                            string tipus = reader.GetString(reader.GetOrdinal("TIPUS"));


                            ta = new TipusAcces(codi, tipus);

                        }
                    }
                }
            }
            return ta;
        }

    }
}

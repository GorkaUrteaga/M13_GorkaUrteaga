using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class ParcDB
    {
        public static ObservableCollection<Parc> GetParcs()
        {
            ObservableCollection<Parc> llista = new ObservableCollection<Parc>();

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        consulta.CommandText = "select * from Parc";

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int codi = reader.GetInt32(reader.GetOrdinal("CODI"));
                            string nom = reader.GetString(reader.GetOrdinal("NOM"));
                            string urlFoto = reader.GetString(reader.GetOrdinal("URL_FOTO"));

                            Parc p = new Parc(codi, nom, urlFoto);

                            llista.Add(p);
                        }
                    }
                }
            }
            return llista;
        }

        public static int GetNumParcs()
        {
            int numParcs = 0;
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        consulta.CommandText = "select count(*) from Parc";

                        numParcs = Convert.ToInt32(consulta.ExecuteScalar());
                    }
                }
            }
            return numParcs;
        }

        public static String GetNomParc(int codi)
        {
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection())
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        DBUtils.CrearParametre("CodiParam", codi, consulta);
                        consulta.CommandText = "select nom from Parc where codi = @CodiParam";

                        return Convert.ToString(consulta.ExecuteScalar());
                    }
                }
            }
        }

    }
}

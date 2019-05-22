using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class AtraccioDB
    {
        public static ObservableCollection<Atraccio> GetAtraccions()
        {
            ObservableCollection<Atraccio> llista = new ObservableCollection<Atraccio>();

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        consulta.CommandText = "select codi,nom,url_foto from atraccio";

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int codi = reader.GetInt32(reader.GetOrdinal("CODI"));
                            string nom = reader.GetString(reader.GetOrdinal("NOM"));
                            string urlFoto = reader.GetString(reader.GetOrdinal("URL_FOTO"));

                            Atraccio a = new Atraccio(codi, nom, urlFoto);

                            llista.Add(a);
                        }
                    }
                }
            }
            return llista;
        }


    }
}

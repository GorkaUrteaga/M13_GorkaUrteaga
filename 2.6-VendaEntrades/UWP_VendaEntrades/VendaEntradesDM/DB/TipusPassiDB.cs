using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Common;
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

                        consulta.CommandText = "select * from Tipus_Passi_Express where arxivat = false";

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

        public static void DeleteTipusPassi(int id)
        {
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();
                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        DBUtils.CrearParametre("IdParam", id, consulta);

                        consulta.CommandText = @"update Tipus_Passi_Express set arxivat = true where id = @IdParam";

                        int actualitzades = consulta.ExecuteNonQuery();
                        if (actualitzades != 1)
                        {
                            throw new Exception("PUFFFFF");
                        }

                    }
                }
            }

        }

        public static bool ExisteixTipusPassi(int id)
        {
            bool existeix;
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();
                    DbTransaction transaccio = connexio.BeginTransaction();
                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        DBUtils.CrearParametre("IdParam", id, consulta);

                        consulta.CommandText = @"select count(*) from Tipus_Passi_Express where id = @IdParam";

                        existeix = Convert.ToInt32(consulta.ExecuteScalar()) == 1;
                    }
                }
            }
            return existeix;
        }

        public static int GetSeguentId()
        {
            int id;
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();
                    DbTransaction transaccio = connexio.BeginTransaction();
                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        consulta.CommandText = @"select ifnull(max(id),0)+1 from Tipus_Passi_Express";

                        id = Convert.ToInt32(consulta.ExecuteScalar());
                    }
                }
            }
            return id;
        }

        public static void UpdateTipusPassi(TipusPassi tipusPassi)
        {
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();
                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {

                        DBUtils.CrearParametre("IdParam", tipusPassi.Id, consulta);
                        DBUtils.CrearParametre("NomParam", tipusPassi.Nom, consulta);
                        DBUtils.CrearParametre("PreuParam", tipusPassi.PreuPerDia, consulta);

                        consulta.CommandText = @"update Tipus_Passi_Express set nom = @NomParam, preu_per_dia = @PreuParam where id = @IdParam";

                        int actualitzades = consulta.ExecuteNonQuery();
                        if (actualitzades != 1)
                        {
                            throw new Exception("PUFFFFF");
                        }

                    }
                }
            }
        }

        public static void InsertTipusPassi(TipusPassi tp)
        {
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();
                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        DbTransaction transaccio = connexio.BeginTransaction();
                        DBUtils.CrearParametre("IdParam", tp.Id, consulta);
                        DBUtils.CrearParametre("NomParam", tp.Nom, consulta);
                        DBUtils.CrearParametre("PreuPerDiaParam", tp.PreuPerDia, consulta);

                        consulta.CommandText = @"insert into Tipus_Passi_Express 
                                                values(@IdParam,@NomParam,@PreuPerDiaParam,false)";

                        int filesInserides = consulta.ExecuteNonQuery();
                        if (filesInserides != 1) throw new Exception("");

                        // desem els canvis de tota la transacció
                        transaccio.Commit();
                    }
                }
            }
        }
    }
}

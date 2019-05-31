using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class TipusPassiAtraccioDB
    {
        public static ObservableCollection<TipusPassiAtraccio> GetTipusPassiAtraccio(int tipusPassi)
        {
            ObservableCollection<TipusPassiAtraccio> llista = new ObservableCollection<TipusPassiAtraccio>();

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        DBUtils.CrearParametre("TipusPassiParam", tipusPassi, consulta);
                        consulta.CommandText = @"select tpa.tipus_passi_express tipus_passi, tpa.atraccio atraccio, ta.tipus tipus_acces, a.nom nom_atraccio, a.url_foto foto_atraccio
                                                from Tipus_Passi_Atraccio tpa join Atraccio a on tpa.atraccio = a.codi 
                                                join Tipus_Acces ta on tpa.tipus_acces = ta.id where tipus_passi_express = @TipusPassiParam";//where Tipus_passi_express = @TipusPassiParam

                        var reader = consulta.ExecuteReader();
                        while (reader.Read()) // per cada Read() avancem una fila en els resultats de la consulta.
                        {
                            int idTipusPassi = reader.GetInt32(reader.GetOrdinal("TIPUS_PASSI"));
                            int idAtraccio = reader.GetInt32(reader.GetOrdinal("ATRACCIO"));
                            string tipusAcces = reader.GetString(reader.GetOrdinal("TIPUS_ACCES"));
                            string nomAtraccio = reader.GetString(reader.GetOrdinal("NOM_ATRACCIO"));
                            string fotoAtraccio = reader.GetString(reader.GetOrdinal("FOTO_ATRACCIO"));

                            TipusPassiAtraccio tpa = new TipusPassiAtraccio(idTipusPassi, idAtraccio, tipusAcces, nomAtraccio, fotoAtraccio);

                            llista.Add(tpa);
                        }
                    }
                }
            }
            return llista;
        }

        public static void UpdateTipusPassiAtraccio(TipusPassiAtraccio tpa, int IdTipusAcces)
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

                        DBUtils.CrearParametre("IdTipusPassiParam", tpa.IdTipusPassi, consulta);
                        DBUtils.CrearParametre("IdAtraccioParam", tpa.IdAtraccio, consulta);
                        DBUtils.CrearParametre("TipusAccesParam", IdTipusAcces, consulta);

                        consulta.CommandText = @"update Tipus_Passi_Atraccio set tipus_acces = @TipusAccesParam where tipus_passi_express = @IdTipusPassiParam and atraccio = @IdAtraccioParam";

                        int actualitzades = consulta.ExecuteNonQuery();
                        if (actualitzades != 1)
                        {
                            throw new Exception("PUFFFFF");
                        }

                    }
                }
            }
        }

        public static void InsertTipusPassiAtraccio(TipusPassiAtraccio tpa, int IdTipusAcces)
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
                        DBUtils.CrearParametre("IdTipusPassiParam", tpa.IdTipusPassi, consulta);
                        DBUtils.CrearParametre("IdAtraccioParam", tpa.IdAtraccio, consulta);
                        DBUtils.CrearParametre("IdTipusAccesParam", IdTipusAcces, consulta);

                        consulta.CommandText = @"insert into Tipus_Passi_Atraccio 
                                                values(@IdTipusPassiParam,@IdAtraccioParam,@IdTipusAccesParam)";

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

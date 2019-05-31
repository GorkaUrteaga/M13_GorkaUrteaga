using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class PassiDB
    {
        public static int GetSeguentCodi()
        {
            int codi;
            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        consulta.CommandText = "select ifnull(max(id),0)+1 from Passi_Express";
                        codi = Convert.ToInt32(consulta.ExecuteScalar());

                    }
                }
            }
            return codi;
        }

        public static void InsertPassi(int idClient, Passi p)
        {
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

                        DBUtils.CrearParametre("IdClientParam", idClient, consulta);
                        DBUtils.CrearParametre("IdParam", p.Id, consulta);
                        DBUtils.CrearParametre("IdTipusPassiParam", p.IdTipusPassi, consulta);
                        DBUtils.CrearParametre("DataParam", p.Data, consulta);

                        consulta.CommandText = @"insert into Passi_Express values 
                                                (@IdParam,
                                                @IdClientParam,
                                                @IdTipusPassiParam,
												@DataParam) ";


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

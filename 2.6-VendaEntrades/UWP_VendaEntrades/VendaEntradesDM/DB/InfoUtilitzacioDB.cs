using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class InfoUtilitzacioDB
    {

        public static void InsertInfoUtilitzacio(InfoUtilitzacio iu)
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

                        DBUtils.CrearParametre("IdPassiParam", iu.IdPassi, consulta);
                        DBUtils.CrearParametre("IdAtraccioParam", iu.IdAtraccio, consulta);
                        DBUtils.CrearParametre("NumeroUsosParam", iu.NumUsos, consulta);
                        DBUtils.CrearParametre("IdTipusAccesParam", iu.IdTipusAcces, consulta);

                        consulta.CommandText = @"insert into Info_Utilitzacio values 
                                                (@IdPassiParam,
                                                @IdAtraccioParam,
												@NumeroUsosParam,
												@IdTipusAccesParam) ";


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

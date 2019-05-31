using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class EntradaParcDB
    {
        public static void InsertEntradaParc(int entrada, int parc)
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
                        DBUtils.CrearParametre("EntradaParam", entrada, consulta);
                        DBUtils.CrearParametre("ParcParam", parc, consulta);

                        consulta.CommandText = @"insert into Entrada_Parc
                                                values(@EntradaParam,@ParcParam)";

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

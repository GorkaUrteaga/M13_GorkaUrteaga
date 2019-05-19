using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class ClientDB
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
                        consulta.CommandText = "select ifnull(max(id),0)+1 from client";
                        codi = Convert.ToInt32(consulta.ExecuteScalar());

                    }
                }
            }
            return codi;
        }

        public static Client GetClient(String nif)
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
                        DBUtils.CrearParametre("NifParam", nif, consulta);

                        consulta.CommandText = @"select Id,Nom,Cognom1,Cognom2 from client where nif =@NifParam";

                        
                    }
                }
            }
        }

        public static void InsertClient(Client c)
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

                        DBUtils.CrearParametre("IdParam", c.Id, consulta);
                        DBUtils.CrearParametre("NifParam", c.Nif, consulta);
                        DBUtils.CrearParametre("NomParam", c.Nom, consulta);
                        DBUtils.CrearParametre("Cognom1Param", c.Cognom1, consulta);
                        DBUtils.CrearParametre("Cognom2Param", c.Cognom2, consulta);
                        DBUtils.CrearParametre("PasswordParam", c.Password, consulta);

                        consulta.CommandText = @"insert into client values 
                                                (@IdParam,
                                                @NifParam,
												@NomParam,
												@Cognom1Param,
												@Cognom2Param,@PasswordParam) ";


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

using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class EntradaDB
    {
        public static int GetSeguentCodi()
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
                        consulta.CommandText = "select ifnull(max(id),0)+1 from entrada";

                        return Convert.ToInt32(consulta.ExecuteScalar());
                    }
                }
            }
        }

        public static int GetEntrada(int id)
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
                        consulta.CommandText = "select ifnull(max(id),0)+1 from entrada";

                        return Convert.ToInt32(consulta.ExecuteScalar());
                    }
                }
            }
        }

        public static bool InsertEntrada(Entrada e)
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
                        DBUtils.CrearParametre("IdParam", e.Id, consulta);
                        DBUtils.CrearParametre("DataParam", e.Data, consulta);
                        DBUtils.CrearParametre("DiesValidesaParam", e.DiesValidesa, consulta);
                        DBUtils.CrearParametre("PreuParam", e.Preu, consulta);
                        DBUtils.CrearParametre("CategoriaParam", e.Categoria.ToString(), consulta);

                        consulta.CommandText = "insert into entrada values (@IdParam, null, @DataParam, @DiesValidesaParam, @PreuParam, @CategoriaParam )";
                        int filesInserides = consulta.ExecuteNonQuery();
                        if (filesInserides != 1) return false;

                        // desem els canvis de tota la transacció
                        transaccio.Commit();
                        // Creem un object de departament nou.
                        return true;
                    }
                }
            }

        }


    }
}

﻿using Microsoft.EntityFrameworkCore;
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
                        consulta.CommandText = "select ifnull(max(id),0)+1 from Entrada";

                        codi = Convert.ToInt32(consulta.ExecuteScalar());
                    }
                }
            }
            return codi;
        }

        public static bool InsertEntrada(Entrada e)
        {
            bool totOk = true;
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

                        consulta.CommandText = "insert into Entrada values (@IdParam, null, @DataParam, @DiesValidesaParam, @PreuParam, @CategoriaParam )";
                        int filesInserides = consulta.ExecuteNonQuery();
                        if (filesInserides != 1) totOk = false;

                        // desem els canvis de tota la transacció
                        transaccio.Commit();
                        // Creem un object de departament nou.
                        
                    }
                }
            }
            return totOk;
        }


    }
}

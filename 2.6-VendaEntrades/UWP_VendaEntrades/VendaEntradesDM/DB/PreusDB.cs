using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;
using VendaEntradesDM.Models;

namespace VendaEntradesDM.DB
{
    public class PreusDB
    {
        public static decimal GetPreu(List<int> parcIds, TipusCategoria categoria, int numDies)
        {
            decimal preu = 0;

            using (DBConnection context = new DBConnection())
            {
                using (var connexio = context.Database.GetDbConnection()) // <== NOTA IMPORTANT: requereix ==>using Microsoft.EntityFrameworkCore;
                {
                    // Obrir la connexió a la BD
                    connexio.Open();

                    // Crear una consulta SQL
                    using (var consulta = connexio.CreateCommand())
                    {
                        
                        DBUtils.CrearParametre("NumDiesParam", numDies, consulta);
                        DBUtils.CrearParametre("Parc1Param", parcIds[0], consulta);
                        DBUtils.CrearParametre("Parc2Param", parcIds[1], consulta);
                        DBUtils.CrearParametre("Parc3Param", parcIds[2], consulta);

                        consulta.CommandText = @"select Preu_Adult, Preu_Nen_Senior, Preu_Discapacitat from Preus where parc1_id = @Parc1Param and parc2_id = @Parc2Param and parc3_id = @Parc3Param and Num_Dies = @NumDiesParam";
                        DbDataReader r = consulta.ExecuteReader();
                        r.Read();
                        
                        switch (categoria)
                        {
                            case TipusCategoria.ADULT:
                                preu = r.GetDecimal(r.GetOrdinal("Preu_Adult"));
                                break;
                            case TipusCategoria.SENIOR:
                                preu = r.GetDecimal(r.GetOrdinal("Preu_Nen_Senior"));
                                break;
                            case TipusCategoria.DISCAPACITAT:
                                preu = r.GetDecimal(r.GetOrdinal("Preu_Discapacitat"));
                                break;
                        }

                        return preu;
                    }
                }
            }
        }

    }
}

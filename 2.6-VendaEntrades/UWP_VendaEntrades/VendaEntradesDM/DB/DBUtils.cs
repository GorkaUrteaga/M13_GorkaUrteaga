using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;

namespace VendaEntradesDM.Models
{
    class DBUtils
    {
        public static void CrearParametre(string pNom, object pValue, DbCommand pConsulta)
        {
            DbParameter param = pConsulta.CreateParameter();
            param.ParameterName = "@" + pNom;
            param.Value = pValue;

            // Cal afegir els paràmetres creats a la llista de paràmetres de la consulta
            pConsulta.Parameters.Add(param);
        }
    }
}

using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    class DBConnection : DbContext
    {
        
        protected override void OnConfiguring(DbContextOptionsBuilder optionBuilder)
        {
            optionBuilder.UseMySQL(@"User Id=root;Host=127.0.0.1;Database=projecte;Password=root");
        }
    }
}

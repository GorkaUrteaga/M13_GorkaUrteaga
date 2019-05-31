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
            optionBuilder.UseMySQL(@"User Id=m2-gurteaga;Host=92.222.27.83;Database=m2_gurteaga;Password=47129014J");
        }
    }
}

using System;
using System.Collections.Generic;
using System.Text;

namespace VendaEntradesDM.Models
{
    public class Client
    {
        private int id;
        private String nif;
        private String nom;
        private String cognom1;
        private String cognom2;
        private String password;

        public Client(int id, string nif, string nom, string cognom1, string cognom2, string password)
        {
            Id = id;
            Nif = nif;
            Nom = nom;
            Cognom1 = cognom1;
            Cognom2 = cognom2;
            Password = password;
        }

        public int Id { get => id; set => id = value; }
        public string Nif { get => nif; set => nif = value; }
        public string Nom { get => nom; set => nom = value; }
        public string Cognom1 { get => cognom1; set => cognom1 = value; }
        public string Cognom2 { get => cognom2; set => cognom2 = value; }
        public string Password { get => password; set => password = value; }
    }
}

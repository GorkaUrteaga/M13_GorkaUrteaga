using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.Security.Cryptography;
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
        private ObservableCollection<Passi> passis;

        private static TextInfo ti = new CultureInfo("es-ES",false).TextInfo;

        private const int PASSWORD_LEN = 10;

        public Client(int id, string nif, string nom, string cognom1, string cognom2, string password)
        {
            Id = id;
            Nif = nif.ToUpper();
            Nom = ti.ToTitleCase(nom.ToLower());
            Cognom1 = ti.ToTitleCase(cognom1.ToLower());
            if(cognom2 != null)
            {
                cognom2 = ti.ToTitleCase(cognom2.ToLower());
            }
            Cognom2 = cognom2;
            if (password == null)
            {
                password = GenerarPassword();
            }
            Password = password;

            passis = new ObservableCollection<Passi>();
            
        }

        private string GenerarPassword()
        {
            Random rnd = new Random();
            byte[] passArray = new byte[PASSWORD_LEN];
            rnd.NextBytes(passArray);
            MD5 md5 = MD5.Create();

            byte[] data = md5.ComputeHash(passArray);

            StringBuilder sBuilder = new StringBuilder();

            for (int i = 0; i < data.Length; i++)
            {
                sBuilder.Append(data[i].ToString("x2"));
            }

            return sBuilder.ToString().Substring(0,PASSWORD_LEN);
            
        }

        public override bool Equals(object obj)
        {
            var client = obj as Client;
            return client != null &&
                   nif == client.nif;
        }

        public override int GetHashCode()
        {
            return -1298672326 + EqualityComparer<string>.Default.GetHashCode(nif);
        }

        public int Id { get => id; set => id = value; }
        public string Nif { get => nif; set => nif = value; }
        public string Nom { get => nom; set => nom = value; }
        public string Cognom1 { get => cognom1; set => cognom1 = value; }
        public string Cognom2 { get => cognom2; set => cognom2 = value; }
        public string Password { get => password; set => password = value; }

        public void AddPassi(Passi p)
        {
            passis.Add(p);
        }

        public void RemovePassi(Passi p)
        {
            passis.Remove(p);
        }

        public String GetCognoms
        {
            get
            {
                string str = cognom1;

                if (cognom2 != null) str += " " + cognom2;
                return str;
            }
        }

        public IEnumerable<Passi> GetPassis()
        {
            return passis;
        }
    }
}

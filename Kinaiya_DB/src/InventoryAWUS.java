public class InventoryAWUS {

        private final String Art_ID;
        private final String Art_Name;
        private final String Art_Subject ;
        private final String Date_Acquired;
        private final String Date_Created;
        private final String Orientation;
        private final String Emp_ID;
        private final String Artist_ID;
   

        
        public InventoryAWUS (String Art_ID,String Art_Name, String Art_Subject, String Date_Acquired, String Date_Created , String Orientation, String Emp_ID, String Artist_ID){
            this.Art_ID = Art_ID;
            this.Art_Name = Art_Name;
            this.Art_Subject = Art_Subject;
            this.Date_Acquired = Date_Acquired;
            this.Date_Created = Date_Created;
            this.Orientation = Orientation;
            this.Emp_ID = Emp_ID;
            this.Artist_ID = Artist_ID;
        }

        public String getArt_ID() {
            return Art_ID;
        }

        public String getArt_Name() {
            return Art_Name;
        }

        public String getArt_Subject() {
            return Art_Subject;
        }
        
        public String getDate_Acquired() {
            return Date_Acquired;
        }

        public String getDate_Created() {
            return Date_Created;
        }
        
        public String getOrientation() {
            return Orientation;
        }

        public String getEmp_ID() {
            return Emp_ID;
        }
        
        public String getArtist_ID() {
            return Artist_ID;
        }
        
        

}

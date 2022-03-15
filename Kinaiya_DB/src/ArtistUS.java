public class ArtistUS {

        private final String Artist_ID;
        private final String Artist_Name;
        private final String Artist_Age;
        
        public ArtistUS (String Artist_ID,String Artist_Name, String Artist_Age ){
            this.Artist_ID = Artist_ID;
            this.Artist_Name = Artist_Name;
            this.Artist_Age = Artist_Age;
        }

        public String getArtist_ID() {
            return Artist_ID;
        }

        public String getArtist_Name() {
            return Artist_Name;
        }

        public String getArtist_Age() {
            return Artist_Age;
        }
}

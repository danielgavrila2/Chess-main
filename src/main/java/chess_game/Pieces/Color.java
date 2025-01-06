package Chess_Business.Pieces;


public enum Color implements java.io.Serializable {
    WHITE {
        @Override
        public String toString() {
            return "White";
        }

    },
    BLACK {
        @Override
        public String toString() {
            return "Black";
        }
    },
    NOCOLOR {
        @Override
        public String toString() {
            return "No Color";
        }
    };

}

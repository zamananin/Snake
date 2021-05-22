public class Vector {
    private int v; // 1 = right, 2 = down, 3 = left, 4 = up, 0 - stay;
    Vector(String s){
        setV(s);
    }

    public String getV() {
        return switch (v){
            case 1 -> "right";
            case 2 -> "down";
            case 3 -> "left";
            case 4 -> "up";
            default -> "stay";
        };
    }

    public void setV(String s) {
        switch (s){
            case "right" -> this.v = 1;
            case "down" -> this.v = 2;
            case "left" -> this.v = 3;
            case "up" -> this.v = 4;
            default -> this.v = 0;
        }
    }
}

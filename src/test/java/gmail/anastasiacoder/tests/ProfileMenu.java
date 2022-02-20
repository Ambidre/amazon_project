package gmail.anastasiacoder.tests;

public enum ProfileMenu {

    DEALS("Today's Deals"),
    SERVICE("Customer Service"),
    SIGN_IN("Registry"),
    GIFT_CARDS("Gift Cards"),
    SELL("Sell");

    private String profileMenu;

    ProfileMenu(String profileMenu) {
        this.profileMenu = profileMenu;
    }

    public String getProfileMenu() {
        return profileMenu;
    }

    @Override
    public String toString() {
        return profileMenu;
    }
}
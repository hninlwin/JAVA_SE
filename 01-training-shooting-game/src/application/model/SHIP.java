package application.model;

public enum SHIP {

	ONE("application/view/images/ship1.png"),
	TWO("application/view/images/ship2.png"), 
	THREE("application/view/images/ship3.png"),
	FOUR("application/view/images/ship4.png");

	private String url;
	
	private SHIP(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}

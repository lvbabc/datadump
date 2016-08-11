package zx.soft.datadump.domain;


public class Type {

	private int id;
	private String name;
	private String birthday;
	private String home;
	private String family;

	@Override
	public String toString() {
		return "('" + id + "','" + name + "','" + birthday + "','" + home + "','" + family + "')";
	}

	public Type(int id, String name, String birthday, String home, String family) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.home = home;
		this.family = family;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

}


package webservices;


public class Tanks {
	private int tank_id;
	private int speed ;
	private int health;
	private int damage;
	private int tank_range;
	private String tank_name;
	
	public Tanks(int id, int sp, int heal, int dam, int range, String name)
	{
		this.tank_id=id;
		this.speed=sp;
		this.health=heal;
		this.damage=dam;
		this.tank_range=range;
		this.tank_name=name;
	}
	public int getId()
	{
		return this.tank_id;
	}
	public int getSpeed()
	{
		return this.speed;
	}
	public int getHealth()
	{
		return this.health;
	}
	public int getDamage()
	{
		return this.damage;
	}
	public int getRange()
	{
		return this.tank_range;
	}
	public String getName()
	{
		return this.tank_name;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tank [id=").append(this.tank_id)
			.append(", speed=").append(this.speed)
			.append(", health=").append(this.health)
			.append(", damage=").append(this.damage)
			.append(", tank_range=").append(this.tank_range)
			.append(", name=").append(this.tank_name).append("]");
		return builder.toString();
	}
}

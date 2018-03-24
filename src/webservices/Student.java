package webservices;

public class Student {
	private int nr_matricol;
	private String nume;
	private String facultate;
	private int an;
	private int nota;

	public Student(int nr_matricol, String nume, String facultate, int an,
			int nota) {
		this.nr_matricol = nr_matricol;
		this.nume = nume;
		this.facultate = facultate;
		this.an = an;
		this.nota = nota;
	}
	public int getNr_matricol() {
		return nr_matricol;
	}
	public String getNume() {
		return nume;
	}
	public String getFacultate() {
		return facultate;
	}
	public int getAn() {
		return an;
	}
	public int getNota() {
		return nota;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [nr_matricol=").append(nr_matricol)
			.append(", nume=").append(nume).append(", facultate=")
			.append(facultate).append(", an=").append(an).append(", nota=").append(nota).append("]");
		return builder.toString();
	}
}
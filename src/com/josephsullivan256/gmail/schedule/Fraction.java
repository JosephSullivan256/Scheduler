package com.josephsullivan256.gmail.schedule;

public class Fraction {
	public final int n, d;
	
	public Fraction(int n, int d) {
		this.n = n;
		this.d = d;
	}
	
	public Fraction(int n) {
		this.n = n;
		this.d = 1;
	}
	
	public Fraction() {
		this.n = 1;
		this.d = 1;
	}
	
	public Fraction plus(Fraction f) {
		return new Fraction(n*f.d + f.n*d,d*f.d).simplified();
	}
	
	public Fraction times(Fraction f) {
		return new Fraction(n*f.n,d*f.d).simplified();
	}
	
	public Fraction minus(Fraction f) {
		return this.plus(f.times(new Fraction(-1)));
	}
	
	public Fraction reciprocal() {
		return new Fraction(d,n);
	}
	
	public Fraction divide(Fraction f) {
		return this.times(f.reciprocal());
	}
	
	public Fraction simplified() {
		int gcd = gcd(n,d);
		return new Fraction(n/gcd,d/gcd);
	}
	
	public static int gcd(int a, int b) {
		if(a==b) return a;
		if(b>a) return gcd(a,b);
		int m = a%b;
		if(m!=0) return gcd(b,m);
		return b;
	}
}

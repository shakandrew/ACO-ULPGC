import java.util.*;

class PrimeChecker {
	public PrimeChecker() {};
	private int lastTestScore = -1;
	private int lastListSize = -1;

	Long powerMod(Long base, Long pow, Long mod) {
		Long res = 1L;
		for (Long i = 0L; i < pow; i++) {
			res = (res * base) % mod;
		}
		return res;
	}

	List<Long> getRandomMNumbers(Long m, Long max) {
		List<Long> solution = new ArrayList<>();
		for (Long i = 1L; i < max; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);
		solution = solution.subList(0, (int)(long)m);
		return solution;
	}

	public Long gcd(Long a, Long b) {
	   if (b==0) return a;
	   return gcd(b,a%b);
	}

	public Long pow(Long base, Long pow) {
		Long res = 1L;
		for (Long i = 0L; i < pow; i++) {
			res *= base;
		}
		return res;
	}

	public Boolean check(int n) {
		int m = 0;
		for (int i = 1;i < n; i <<=1, m++);
		return check(n, m);
	}

	public Boolean check(int n, int m) {
		return check(new Long(n), new Long(m));
	}

	public int getLastResult() { return lastTestScore; }
	public int getLastListSize() { return lastListSize; }

	public Boolean check(Long n, Long m) {
		List<Long> l = getRandomMNumbers(m, n);
		lastListSize = l.size();
		for (int i = 0; i < l.size(); i++) {
			Long b = l.get(i);
			lastTestScore = i + 1;
			if (powerMod(b, n-1, n) != 1L) {
				return false;
			}
			for (Long d = 1L; d <= n - 1L; d <<= 1) {
				Long k = (n - 1) / d;
				if (k * d == n - 1) {
					Long mcd = gcd(pow(b, k) - 1, n);
					if (1L < mcd && mcd < n) {
						return false;
					}
				}

			}
		}
		return true;
	}
}

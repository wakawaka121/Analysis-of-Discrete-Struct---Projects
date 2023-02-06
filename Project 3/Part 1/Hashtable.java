
public class Hashtable {
	private Pair[] linearTable;
	private int mod = 11;
	private int size;
	
	private class Pair{
		private String key;
		private int value;
		public boolean deleted;
		
		public Pair(String key, int value) {
			this.key = key;
			this.value = (Integer) value;
			this.deleted = false;
		}
		
		public String getKey() {
			return key;
		}
		
		public Integer getValue() {
			return value;
		}
		
		public int hashCode() {
			return Math.abs(key.hashCode());
		}
	}
	
	public Hashtable() {
		linearTable = new Pair[11];
		size = 0;
	}
	
	public Hashtable(int m) {
		linearTable = new Pair[m];
		size = 0;
	}
	
	public void put(String key, Integer val) {
		Pair newElement = new Pair(key,val);
		int hashValue = newElement.hashCode()%mod;
		if(linearTable[hashValue] == null || linearTable[hashValue].deleted) {
			linearTable[hashValue] = newElement;
			size++;
		} else {
			hashValue = linearProbe(hashValue, linearTable);
			linearTable[hashValue] = newElement;
			size++;
		}
		if(size/mod > (1/2)) {
			growArray2();
		}	
	}
	
	private int linearProbe(int startHash, Pair[] table) {
		if(startHash == mod-1) {
			startHash = 0;
		} else {
			startHash++;
		}
		while(table[startHash] !=null) {
			if(table[startHash].deleted) {
				return startHash;
			}
			if(startHash == mod-1) {
				startHash = 0;
			} else {
				startHash++;
			}
		}
		return startHash;
		
	}
	
	private void shrinkArray() {
		int newCapacity = linearTable.length/2;
		newCapacity = findNextPrime(newCapacity);
		Pair[] resize = new Pair[newCapacity];
		mod = newCapacity;
		for(int hashIndex = 0; hashIndex < linearTable.length; hashIndex ++) {
			if(linearTable[hashIndex] != null && !linearTable[hashIndex].deleted) {
				int hashValue = linearTable[hashIndex].hashCode()%mod;
				resize[hashValue] = linearTable[hashIndex];
			}
		}
		linearTable = resize;
	}

	private int findNextPrime(int value) {
		if(value < 11) {
			return 11;
		}
		int isPrime = 1;
		for(int i = 2; i < value; i++) {
			if(value%i == 0) {
				isPrime = 0;
			}
		}
		if(isPrime == 1) {
			return value;
		} else {
			value++;
			for (int i = 2; i < value; i++) {
		         if(value%i == 0) {
		            value++;
		            i=2;
		         } else {
		            continue;
		         }
		      }
		      return value;
		}	
	}

	private void growArray() {
		int newCapacity = linearTable.length*2;
		newCapacity = findNextPrime(newCapacity);
		Pair[] resize = new Pair[newCapacity];
		mod = newCapacity;
		for(int hashIndex = 0; hashIndex < linearTable.length; hashIndex ++) {
			if(linearTable[hashIndex] != null && !linearTable[hashIndex].deleted) {
				int hashValue = linearTable[hashIndex].hashCode()%mod;
				resize[hashValue] = linearTable[hashIndex];
			}
		}
		linearTable = resize;
		
	}
	
	private void growArray2() {
		int newCapacity = linearTable.length*2;
		newCapacity = findNextPrime(newCapacity);
		Pair[] resize = new Pair[newCapacity];
		mod = newCapacity;
		for(int hashIndex = 0; hashIndex < linearTable.length; hashIndex ++) {
			if(linearTable[hashIndex] != null && !linearTable[hashIndex].deleted) {
				int hashValue = linearTable[hashIndex].hashCode()%mod;
				if(resize[hashValue] == null){
					resize[hashValue] = linearTable[hashIndex];
				} else {
					hashValue = linearProbe(hashValue,resize);
					resize[hashValue] = linearTable[hashIndex];
				}
			}
		}
		linearTable = resize;
	}
	
	

	public Integer get(String key) {
		if(contains(key)) {
			int hashIndex = Math.abs(key.hashCode())%mod;
			if(linearTable[hashIndex].getKey().equals(key)) {
				return linearTable[hashIndex].getValue();
			} else {
				hashIndex = probeGrouping(hashIndex,key);
				return linearTable[hashIndex].getValue();
			}
		} else {
			return null;
		}
	}
	
	private int probeGrouping(int startIndex, String key) {
		if(startIndex == mod-1) {
			startIndex = 0;
		} else {
			startIndex++;
		}
		while(true) {
			if(linearTable[startIndex].getKey().equals(key)) {
				return startIndex;
			}
			if(startIndex == mod-1) {
				startIndex = 0;
			} else {
				startIndex++;
			}
		}
	}
	
	public Integer delete(String key) {
		Pair keyPair;
		if(contains(key)) {
			int indexKey = Math.abs(key.hashCode()%mod);
			if(linearTable[indexKey].getKey().equals(key)) {
				keyPair = linearTable[indexKey];
				linearTable[indexKey].deleted = true;
				//return keyPair.getValue();
			} else {
				indexKey = probeGrouping(indexKey,key);
				keyPair = linearTable[indexKey];
				linearTable[indexKey].deleted = true;
			}
			size--;
			if(size/mod < 1/8) {
				shrinkArray();
			}
			return keyPair.getValue();
		}
		return null;
	}
	
	public boolean contains(String key) {
		for(int i = 0; i < mod; i++) {
			if(linearTable[i] != null && !linearTable[i].deleted) {
				if(linearTable[i].getKey().equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
}

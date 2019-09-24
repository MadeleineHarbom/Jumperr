package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import storage.LocalStorage;

public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private String address;
    private int telephoneNumber;
    private String username;
    private String password;
    private int admin;
    private HashMap<User, Integer> jumpReviews  = new HashMap<>();
    private HashMap<User, Integer> driveReviews  = new HashMap<>();

    public User(String name, String email, String address, int telephoneNumber, String username, String password) {
        this.id = generateUniqueID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.username = username;
        this.password = password;
        this.admin = 0;
    }

    public int generateUniqueID() {
        int id = 0;

        // hvis det er første bruger, får brugeren ID = 1 ellers får brugeren den sidste
        // brugeres id +1
        if (LocalStorage.getUsers().size() == 0) {
            id = 1;
        } else {
            int lastUserIndex = LocalStorage.getUsers().size() - 1;
            id = LocalStorage.getUsers().get(lastUserIndex).id + 1;
        }
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // et user-objekt er det samme hvis det har samme navn
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    // et user-objekt er det samme hvis det har samme navn
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    public void addDriveReview(User u, int i) {
    	this.driveReviews.put(u, i);

    }
    
    public void addJumpReview(User u, int i) {
 
    	
    	this.jumpReviews.put(u, i);
    }
    
    public double getDriveScore() {
    	double d = 0;
    	Iterator<HashMap.Entry<User, Integer>> itr = driveReviews.entrySet().iterator();
    	while(itr.hasNext()) {
    		d += itr.next().getValue();
    	}
    	return d;
    }
    
    public double getJumpScore() {
    	double d = 0.0;
    	
    	Iterator<HashMap.Entry<User, Integer>> itr = jumpReviews.entrySet().iterator();
    	while(itr.hasNext()) {
    		d += itr.next().getValue();
    	}
    	
    	return d;
    }
    

}

package Main;

public class vehicleinstance {
    public String vehicleModel;
    public String vehicleType;
    public String vehicleNumber;

    public  int vehiclecapacity;
    public void setmodel(String model){
        this.vehicleModel = model;
    }
    public void setVehicleType(String type){
        this.vehicleType = type;
    }
    public void setVehicleNumber(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }
    public void setVehiclecapacity(int vehiclecapacity){
        this.vehiclecapacity = vehiclecapacity;
    }
    public vehicle build(){
        return  new vehicle(this.vehicleModel, this.vehicleType,this.vehicleNumber,this.vehiclecapacity);
    }

    public void reset(){
        this.vehiclecapacity = 0;
        this.vehicleModel = null;
        this.vehicleType = null;
    }


    public class vehicle{
        public String vehicleModel;
        public String vehicleType;
        public String vehicleNumber;

        public  int vehiclecapacity;

        public vehicle(String vehicleModel, String vehicleType, String vehicleNumber, int vehiclecapacity) {
            this.vehicleModel = vehicleModel;
            this.vehicleType = vehicleType;
            this.vehicleNumber = vehicleNumber;
            this.vehiclecapacity = vehiclecapacity;
        }

        @Override
        public String toString(){
            return " Model: "+this.vehicleModel + " \nType: " + this.vehicleType + " \nNumber: "+this.vehicleNumber +" \nCapacity: "+this.vehiclecapacity;
        }

    }

}

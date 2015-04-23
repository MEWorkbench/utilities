package pt.uminho.ceb.biosystems.mew.utilities.datastructures;


public enum PrimitiveTypes {
		
	STRING{
		
		public String getObjectType(String input){
			return input;
		}
		
		public Boolean isConvertable(Object input){
			return true;
		}
		
	},
	INTEGER{
		public Integer getObjectType(String input){
			return Integer.parseInt(input);
		}
		
		public Boolean isConvertable(Object input){
			boolean ret = true;
			
			try {
				Integer.parseInt(input.toString());
			} catch (Exception e) {
				ret = false;
			}
			
			return ret;
		}
	},
	
	DOUBLE{
		// TODO: e preciso difinir este tipo de configuracoes! exemplo: valores para o infinito...
		public Double getObjectType(String input){
			return Double.parseDouble(input);
		}
		
		public Boolean isConvertable(Object input){
			
			boolean ret = true;
			try {
				Double.parseDouble(input.toString());
			} catch (Exception e) {
				ret = false;
			}
			
			return ret;
		}
	},
	
	BOOLEAN {
		public Boolean getObjectType(String input){
			return Boolean.valueOf(input);
		}
		
		public Boolean isConvertable(Object input){
			
			boolean ret = true;
			try {
				Boolean.valueOf(input.toString());
			} catch (Exception e) {
				ret = false;
			}
			
			return ret;
		}
	};
	
	public Object getObjectType(String input){
		return this.getObjectType(input);
		
	}
	
	public Boolean isConvertable(Object input){
		return this.isConvertable(input);
	}
}

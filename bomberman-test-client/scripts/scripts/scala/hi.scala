object hi {
  
    //def main(args: Array[String]): Unit = {
    def main(): Unit = {
      println("Hello, world!")
      
       //def m() = Constants.get() //v = same
      def m() = Constants get()
      
      var x = m()
      println(x)
    }
    
}
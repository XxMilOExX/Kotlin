import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.attribute.AclEntry.Builder
import kotlin.reflect.typeOf


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

fun main() {
    var breakFlag = true;
    while(breakFlag) {
        println("Hi, how many people do you want to watch a vacation for?")
        val participants = readln().toInt();
        if (participants <= 0) {
            println("You entered an incorrect value")
        }
        println("How much money do you have in $")
        var price = readln().toInt();
        if (price < 0) {
            price = 0;
        }
        for (i in 0..5) {
            val client = HttpClient.newBuilder().build();
            val Api = ApiCreate(participants, price, client)
            println(Api?.body())
        }

        println("Do you want to continue (yes or not)")

        breakFlag = readln() == "yes"
    }
}

fun ApiCreate(participants: Int, price: Int, client: HttpClient): HttpResponse<String>? {
    val uri = URI.create("http://www.boredapi.com/api/activity?participants=${participants}&price=${price}")
    val request = HttpRequest.newBuilder().uri(uri).build();
    return client.send(request, HttpResponse.BodyHandlers.ofString());

}



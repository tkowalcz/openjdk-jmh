package openjdk.java.lang;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringContainsMicrobenchmark {

    private String shortCupcakeRecipe = "Cupcake ipsum dolor sit amet tiramisu jelly. Cannabis";

    private String mediumCupcakeRecipe = "Cupcake ipsum dolor sit amet. Brownie gummies ice cream. Cookie lemon drops chocolate cupcake cheesecake marzipan dragée croissant tootsie roll. Sugar plum cake apple pie cake chocolate marshmallow chocolate. Topping tart lemon drops cotton candy. Croissant chocolate gingerbread jelly-o cake chocolate bar. Lollipop danish chupa chups ice cream donut sweet roll gummies sugar plum. Cotton candy cupcake fruitcake chupa chups cake tootsie roll. Halvah lemon drops candy jelly-o muffin danish candy canes bonbon. Fruitcake sweet roll marzipan croissant soufflé marshmallow croissant fruitcake. Jelly-o sweet roll sesame snaps. Cake jelly beans sweet jelly beans pastry lemon drops gummies candy canes. Gingerbread ice cream gummies croissant macaroon icing tiramisu sugar plum lemon drops. Pudding cake cupcake.\n" +
            "Bear claw powder croissant tootsie roll pie. Candy canes gingerbread chupa chups icing dragée. Marshmallow donut fruitcake gummi bears. Jelly beans carrot cake bonbon. Lemon drops powder chocolate bar cotton candy chupa chups cotton candy tootsie roll bear claw wafer. Powder icing pie sugar plum macaroon dragée. Brownie marshmallow marshmallow muffin toffee oat cake tart. Marshmallow muffin pudding gummies danish jelly apple pie dessert. Chupa chups candy canes croissant pudding. Icing cookie pudding jelly chupa chups. Topping marzipan gingerbread donut sesame snaps macaroon. Carrot cake marzipan chocolate cake tiramisu. Pudding icing gummies pie lollipop soufflé gingerbread tiramisu jelly. Cannabis";

    private String longCupcakeRecipe = "Cupcake ipsum dolor sit amet. Brownie gummies ice cream. Cookie lemon drops chocolate cupcake cheesecake marzipan dragée croissant tootsie roll. Sugar plum cake apple pie cake chocolate marshmallow chocolate. Topping tart lemon drops cotton candy. Croissant chocolate gingerbread jelly-o cake chocolate bar. Lollipop danish chupa chups ice cream donut sweet roll gummies sugar plum. Cotton candy cupcake fruitcake chupa chups cake tootsie roll. Halvah lemon drops candy jelly-o muffin danish candy canes bonbon. Fruitcake sweet roll marzipan croissant soufflé marshmallow croissant fruitcake. Jelly-o sweet roll sesame snaps. Cake jelly beans sweet jelly beans pastry lemon drops gummies candy canes. Gingerbread ice cream gummies croissant macaroon icing tiramisu sugar plum lemon drops. Pudding cake cupcake.\n" +
            "Bear claw powder croissant tootsie roll pie. Candy canes gingerbread chupa chups icing dragée. Marshmallow donut fruitcake gummi bears. Jelly beans carrot cake bonbon. Lemon drops powder chocolate bar cotton candy chupa chups cotton candy tootsie roll bear claw wafer. Powder icing pie sugar plum macaroon dragée. Brownie marshmallow marshmallow muffin toffee oat cake tart. Marshmallow muffin pudding gummies danish jelly apple pie dessert. Chupa chups candy canes croissant pudding. Icing cookie pudding jelly chupa chups. Topping marzipan gingerbread donut sesame snaps macaroon. Carrot cake marzipan chocolate cake tiramisu. Pudding icing gummies pie lollipop soufflé gingerbread.\n" +
            "Marzipan carrot cake halvah. Danish chocolate cake tootsie roll. Soufflé danish icing chupa chups lemon drops carrot cake sweet apple pie cake. Danish macaroon bear claw sweet gummies. Tart jujubes sugar plum apple pie powder marshmallow gummies caramels donut. Apple pie tootsie roll carrot cake tiramisu apple pie marshmallow marzipan carrot cake. Gummies topping pudding biscuit oat cake gummi bears pastry oat cake. Powder jelly chocolate sweet roll candy canes cake cookie. Cupcake cake halvah bonbon. Sweet cookie cake pudding bear claw. Icing dragée pie. Chocolate cake tiramisu pastry cupcake fruitcake muffin chupa chups.\n" +
            "Gummi bears powder sugar plum. Topping chupa chups ice cream sesame snaps oat cake lemon drops. Candy chupa chups gummi bears muffin halvah candy canes carrot cake wafer. Halvah marshmallow cupcake chupa chups bear claw caramels powder cheesecake. Cake jelly jelly pie tart biscuit cake. Candy brownie toffee chocolate bar bonbon icing. Tiramisu oat cake icing jelly-o dessert. Pudding sweet roll bonbon halvah croissant lemon drops oat cake cookie. Biscuit jelly tiramisu cookie icing bear claw apple pie. Dragée macaroon sweet roll tiramisu. Bear claw cheesecake sesame snaps. Marzipan bonbon croissant. Fruitcake jelly-o wafer marshmallow liquorice. Marzipan icing tootsie roll jujubes cotton candy cookie marzipan sesame snaps bear claw.\n" +
            "Bear claw candy jelly beans muffin marzipan lemon drops danish. Sweet biscuit tiramisu caramels jujubes gingerbread jelly ice cream. Bonbon halvah carrot cake croissant ice cream chupa chups. Tiramisu danish donut chocolate bar bear claw jelly tart carrot cake. Cake jelly gingerbread sweet chocolate oat cake. Marzipan chocolate danish pie jelly-o candy gummi bears. Muffin cookie lemon drops tart bonbon marzipan topping. Sweet roll sweet roll pudding lollipop cake soufflé sesame snaps. Donut sweet roll tiramisu. Gingerbread tiramisu liquorice sesame snaps cheesecake jujubes chocolate chocolate. Oat cake tiramisu dragée sugar plum jelly-o. Jelly beans toffee macaroon pudding apple pie biscuit sweet. Sweet roll sugar plum danish gingerbread pie tiramisu. Cheesecake powder caramels cupcake tiramisu jelly. Cannabis";

    // Cannabis is selected so there will be multiple partial matches against eg. can-dy, ca-ke, ca-nes etc.
    // xyz is selected so that there are no partial matches
    @SuppressWarnings("unused")
    @Param({"cannabis", "xyz", "Cheesecake powder caramels cupcake tiramisu jelly"})
    private String searchString;
    private StringBuilder searchCharSequence;

    @Setup
    public void setup() {
        searchCharSequence = new StringBuilder(searchString);
    }

    @Benchmark
    public boolean shortStringContainsCharSequence() {
        return shortCupcakeRecipe.contains(searchCharSequence);
    }

    @Benchmark
    public boolean shortStringContainsString() {
        return shortCupcakeRecipe.contains(searchString);
    }

    @Benchmark
    public boolean mediumStringContainsCharSequence() {
        return mediumCupcakeRecipe.contains(searchCharSequence);
    }

    @Benchmark
    public boolean mediumStringContainsString() {
        return mediumCupcakeRecipe.contains(searchString);
    }

    @Benchmark
    public boolean longStringContainsCharSequence() {
        return longCupcakeRecipe.contains(searchCharSequence);
    }

    @Benchmark
    public boolean longStringContainsString() {
        return longCupcakeRecipe.contains(searchString);
    }
}

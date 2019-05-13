import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {
    private static String Task1(List<String> names) {
        return IntStream.range ( 0, names.size () )
                .filter ( index -> index % 2 == 1 )
                .mapToObj ( index -> String.format ( "%d. %s", index, names.get ( index ) ) )
                .collect ( Collectors.joining ( ", ", "", "" ) );
    }

    public static List<String> Task2(List<String> uperList) {
        return uperList.stream ()
                .map ( String::toUpperCase )
                .sorted ( Comparator.reverseOrder () )
                .collect ( Collectors.toList () );
    }

    public static String Task3(List<String> numRegex) {
        return numRegex.stream ()
                .flatMap ( str -> Arrays.stream ( str.split ( ", " ) ) )
                .sorted ()
                .collect ( Collectors.joining ( ", ", "", "" ) );
    }

    public static java.util.stream.Stream<Long> Task4(long seed, long a, long c, long m) {
        return java.util.stream.Stream
                .iterate ( seed, n -> (a * n + c) % m )
                .skip ( 1 );
    }

    public static <T> java.util.stream.Stream<T> Task5(java.util.stream.Stream<T> first, java.util.stream.Stream<T> second) {
        Iterator<T> firstIterator = first.iterator ();
        Iterator<T> secondIterator = second.iterator ();
        java.util.stream.Stream.Builder<T> builder = java.util.stream.Stream.builder ();
        while (firstIterator.hasNext () && secondIterator.hasNext ()) {
            builder.add ( firstIterator.next () );
            builder.add ( secondIterator.next () );
        }
        return builder.build ();
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<> ();
        List<String> numbers = new ArrayList<> ();
        long seed = 42;
        long a = 25214903917L;
        long c = 11L;
        long m = 2L << 48L;
        names.add ( "Anatoli" );
        names.add ( "Valeria" );
        names.add ( "Ararat" );
        names.add ( "Luba" );
        numbers.add ( "1, 2, 4" );
        numbers.add ( "7, 3" );
        numbers.add ( "6, 5" );
        System.out.println ( Task1 ( names ) );
        for (String name : Task2 ( names )) {
            System.out.println ( name );
        }
        System.out.println ( Task3 ( numbers ) );

        long arr[] = Task4 ( seed, a, c, m )
                .limit ( 10 )
                .mapToLong ( x -> (long) x )
                .toArray ();
        for (Long n : arr) {
            System.out.println ( n );
        }
        java.util.stream.Stream s1 = java.util.stream.Stream.of ( "one", "three", "five" );
        java.util.stream.Stream s2 = java.util.stream.Stream.of ( "two", "four", "six", "seven", "eight" );
        Task5 ( s1, s2 ).forEach ( System.out::println );
    }
}

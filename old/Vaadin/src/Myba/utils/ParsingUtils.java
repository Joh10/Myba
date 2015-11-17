package Myba.utils;

import DAO.PropositionStageDAO;
import DAO.StageDAO;
import DAO.TechnologieDAO;
import Server.Adresse;
import Server.PropositionStage;
import Server.Technologie;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 18-05-15 at 23:08.
 */

public final class ParsingUtils
{
    private final static String[] commonStageWords = {"a", "de", "au", "aux", "en", "stage", "proximite", "proche"};
    private final static String[] commonTFEWords = {"a", "de", "en", "tfe"};
    private final static Pattern kmPattern = Pattern.compile("(\\d+)\\s*((kilomètres)|(km)|(kilomètre)|(kilometre)|(kilometres))");
    private final static List<String> technologies;
    private final static Set<String> entreprises;

    static
    {
        technologies = new ArrayList<>();
        entreprises = new HashSet<>();

        technologies.addAll(TechnologieDAO.getInstance().findAll().stream().map(Technologie::getNom).collect(Collectors.toList()));
        entreprises.addAll(PropositionStageDAO.getInstance().findAll().stream().map(e -> e.getLieuStage().getEntreprise()).collect(Collectors.toList()));
        entreprises.addAll(StageDAO.getInstance().findAll().stream().map(e -> e.getPropositionStage().getLieuStage().getEntreprise()).collect(Collectors.toList()));
    }

    private ParsingUtils()
    {
    }

    public static String normalize(final String input)
    {
        if (input == null) return null;

        final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        final String decomposed = Normalizer.normalize(input, Normalizer.Form.NFD);

        return pattern.matcher(decomposed).replaceAll("").toLowerCase().trim();
    }

    private static boolean containsAllWords(final String input, String... words)
    {
        for (String word : words)
            if (!input.contains(word)) return false;

        return true;
    }

    public static String deleteWords(final String input, String... words)
    {
        String temp = input;

        for (String word : words)
            temp = temp.replaceAll("\\b" + word + "\\b", "");

        return temp;
    }

    public static String deleteCommonStageWords(final String input)
    {
        String temp = input;

        for (String word : commonStageWords)
            temp = temp.replaceAll("\\b" + word + "\\b", "");

        return temp.trim();
    }


    public static String deleteCommonTFEWords(final String input)
    {
        String temp = input;

        for (String word : commonTFEWords)
            temp = temp.replaceAll("\\b" + word + "\\b", "");

        return temp.trim();
    }

    public static boolean isATechnology(String word)
    {
        return technologies.contains(word);
    }


    public static boolean isAEntreprise(String word)
    {
        return entreprises.contains(word);
    }

    public static boolean AddressMatch(Adresse lieu, String phrase)
    {
        return containsAllWords(lieu.toString(), phrase.toLowerCase().trim().split(" "));
    }

    public static boolean StageHaveOneOfTech(PropositionStage propositionStage, List<String> technologies)
    {
        for (Technologie t : propositionStage.getTechnologies())
            for (String s : technologies)
                if (t.getNom().toLowerCase().equals(s.toLowerCase())) return true;

        return false;
    }

    public static boolean StageIsFromEntreprise(PropositionStage propositionStage, List<String> entreprises)
    {
        for (String s : entreprises)
            if (s.equals(propositionStage.getLieuStage().getEntreprise())) return true;

        return false;
    }

    public static String extractDistance(String input)
    {
        Matcher matcher = kmPattern.matcher(input);

        //noinspection LoopStatementThatDoesntLoop
        while (matcher.find()) return matcher.group().trim();

        return "";
    }

}

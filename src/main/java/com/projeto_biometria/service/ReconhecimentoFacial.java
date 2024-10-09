package com.projeto_biometria.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class ReconhecimentoFacial {

    private static AmazonRekognition rekognitionClient;

    public ReconhecimentoFacial() {
        this.rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
    }

    public static boolean compareFaces(String sourceImagePath, byte[] targetImageBytes, Float similarityThreshold) throws Exception {
        ByteBuffer sourceImageBytes;

        // Carrega a imagem de origem
        try (InputStream inputStream = new FileInputStream(new File(sourceImagePath))) {
            sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }

        // Cria a imagem para requisição
        Image source = new Image().withBytes(sourceImageBytes);
        Image target = new Image().withBytes(ByteBuffer.wrap(targetImageBytes));

        // Configura a requisição de comparação de faces
        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        // Realiza a chamada à API
        CompareFacesResult result = rekognitionClient.compareFaces(request);
        List<CompareFacesMatch> faceMatches = result.getFaceMatches();

        return !faceMatches.isEmpty(); // Retorna verdadeiro se houver uma correspondência
    }
}
